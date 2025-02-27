package io.dotanuki.norris.search.tests

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.dotanuki.coroutines.testutils.CoroutinesTestHelper
import io.dotanuki.coroutines.testutils.collectForTesting
import io.dotanuki.coroutines.testutils.unwrapError
import io.dotanuki.norris.architecture.StateContainer
import io.dotanuki.norris.architecture.StateMachine
import io.dotanuki.norris.architecture.TaskExecutor
import io.dotanuki.norris.architecture.UnsupportedUserInteraction
import io.dotanuki.norris.architecture.UserInteraction
import io.dotanuki.norris.architecture.UserInteraction.OpenedScreen
import io.dotanuki.norris.architecture.ViewState.Failed
import io.dotanuki.norris.architecture.ViewState.FirstLaunch
import io.dotanuki.norris.architecture.ViewState.Loading
import io.dotanuki.norris.architecture.ViewState.Success
import io.dotanuki.norris.domain.ComposeSearchOptions
import io.dotanuki.norris.domain.errors.NetworkingError.OperationTimeout
import io.dotanuki.norris.domain.model.SearchOptions
import io.dotanuki.norris.search.SearchPresentation
import io.dotanuki.norris.search.SearchPresentation.QueryValidation
import io.dotanuki.norris.search.SearchPresentation.Suggestions
import io.dotanuki.norris.search.SearchViewModel
import io.dotanuki.norris.search.ValidateQuery
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTests {

    @get:Rule val helper = CoroutinesTestHelper()

    private val usecase = mock<ComposeSearchOptions>()

    private lateinit var viewModel: SearchViewModel

    @Before fun `before each test`() {
        val stateMachine =
            StateMachine<SearchPresentation>(
                executor = TaskExecutor.Synchronous(helper.scope),
                container = StateContainer.Unbounded(helper.scope)
            )

        viewModel = SearchViewModel(usecase, stateMachine)
    }

    @Test fun `should report failure when fetching from remote`() {
        runBlocking {

            // Given
            val emissions = viewModel.bind().collectForTesting()

            // When
            whenever(usecase.execute()).thenAnswer { throw OperationTimeout }

            // And
            viewModel.handle(OpenedScreen).join()

            // Then
            val viewStates = listOf(
                FirstLaunch,
                Loading.FromEmpty,
                Failed(OperationTimeout)
            )

            assertThat(emissions).isEqualTo(viewStates)
        }
    }

    @Test fun `should report unsupported interaction`() {

        val result = runCatching {
            viewModel.handle(UserInteraction.RequestedFreshContent)
        }

        assertThat(unwrapError(result)).isEqualTo(UnsupportedUserInteraction)
    }

    @Test fun `should display suggestions`() {
        runBlocking {

            // Given
            val emissions = viewModel.bind().collectForTesting()
            val options = SearchOptions(
                history = emptyList(),
                recommendations = listOf("dev", "humor", "money")
            )
            // When
            whenever(usecase.execute()).thenReturn(options)

            // And
            viewModel.handle(OpenedScreen).join()

            // Then

            val viewStates = listOf(
                FirstLaunch,
                Loading.FromEmpty,
                Success(
                    Suggestions(options)
                )
            )

            assertThat(emissions).isEqualTo(viewStates)
        }
    }

    @Test fun `should validate incoming query`() {
        runBlocking {

            // Given
            val emissions = viewModel.bind().collectForTesting()

            // When
            viewModel.handle(ValidateQuery("Norris")).join()

            // Then

            val viewStates = listOf(
                FirstLaunch,
                Loading.FromEmpty,
                Success(
                    QueryValidation(true)
                )
            )

            assertThat(emissions).isEqualTo(viewStates)
        }
    }
}