# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn com.bangkit.core.data.ProductRepository
-dontwarn com.bangkit.core.data.Result$Error
-dontwarn com.bangkit.core.data.Result$Loading
-dontwarn com.bangkit.core.data.Result$Success
-dontwarn com.bangkit.core.data.Result
-dontwarn com.bangkit.core.data.local.LocalDataSource
-dontwarn com.bangkit.core.data.remote.ApiService
-dontwarn com.bangkit.core.data.remote.RemoteDataSource
-dontwarn com.bangkit.core.di.LocalModule
-dontwarn com.bangkit.core.di.LocalModule_ProvideDatabaseFactory
-dontwarn com.bangkit.core.di.LocalModule_ProvideProductDaoFactory
-dontwarn com.bangkit.core.di.NetworkModule
-dontwarn com.bangkit.core.di.NetworkModule_ProvideApiServiceFactory
-dontwarn com.bangkit.core.di.NetworkModule_ProvideOkHttpClientFactory
-dontwarn com.bangkit.core.di.RepositoryModule
-dontwarn com.bangkit.core.di.RepositoryModule_ProvideRepositoryFactory
-dontwarn com.bangkit.core.domain.model.Product
-dontwarn com.bangkit.core.domain.repository.IProductRepository
-dontwarn com.bangkit.core.domain.usecase.ProductInteractor
-dontwarn com.bangkit.core.domain.usecase.ProductUseCase
-dontwarn com.bangkit.core.utils.AppExecutors

