import configs.KotlinConfig

// Versions for project parameters and forEachDependency

object Libraries {

    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${KotlinConfig.version}"
    val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinSerialization}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitKotlinSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerialization}"

    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    val coreAndroidx = "androidx.core:core-ktx:${Versions.coreAndroidx}"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    val coroutinesDebug = "org.jetbrains.kotlinx:kotlinx-coroutines-debug:${Versions.coroutines}"

    val kodein = "org.kodein.di:kodein-di-generic-jvm:${Versions.kodein}"
    val kodeinConf = "org.kodein.di:kodein-di-conf-jvm:${Versions.kodein}"

    val jUnit = "junit:junit:${Versions.junit}"
    val assertj = "org.assertj:assertj-core:${Versions.assertj}"
    val burster = "com.github.ubiratansoares:burster:${Versions.burster}"
    val barista = "com.schibsted.spain:barista:${Versions.barista}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoDexMaker = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.mockitoDexMaker}"
    val androidTestCore = "androidx.test:core:${Versions.androidxTest}"
    val androidTestCoreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"
    val androidTestExtJunit = "androidx.test.ext:junit:${Versions.jUnitKtx}"
    val androidTestExtJunitKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitKtx}"
    val androidTestRunner = "androidx.test:runner:${Versions.androidxTest}"
    val androidTestRules = "androidx.test:rules:${Versions.androidxTest}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val roboletric = "org.robolectric:robolectric:${Versions.roboletric}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    val slf4jNoOp = "org.slf4j:slf4j-nop:${Versions.slf4j}"

    private object Versions {
        const val kotlinSerialization = "0.11.0"
        const val okHttp = "4.0.0-RC1"
        const val retrofit = "2.6.0"
        const val retrofitKotlinSerialization = "0.4.0"
        const val coroutines = "1.3.0-M2"
        const val kodein = "6.2.1"
        const val lifecycle = "2.2.0-alpha01"
        const val coreAndroidx = "1.2.0-alpha01"
        const val materialDesign = "1.1.0-alpha06"
        const val appCompat = "1.1.0-beta01"
        const val recyclerView = "1.1.0-alpha06"
        const val junit = "4.12"
        const val assertj = "3.11.1"
        const val burster = "0.1.1"
        const val mockitoKotlin = "2.1.0"
        const val mockitoDexMaker = "2.19.0"
        const val jUnitKtx = "1.1.2-alpha01"
        const val androidxTest = "1.2.0"
        const val espresso = "3.2.0"
        const val roboletric = "4.3"
        const val barista = "3.1.0"
        const val slf4j = "1.7.25"
    }
}