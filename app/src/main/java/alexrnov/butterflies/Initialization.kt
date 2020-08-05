package alexrnov.butterflies

import android.app.Application
import android.content.Context
import android.util.Log
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

// contextComponent lives in the Application class to share its lifecycle
class Initialization : Application() {

  // Reference to the application graph that is used across the whole app
  //val appComponent = DaggerApplicationComponent.create()

  lateinit var appComponent: ApplicationComponent

  override fun onCreate() {

    super.onCreate()


    appComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(this.applicationContext))
            .build()

    // Reference to the application graph that is used across the whole app
    contextComponent = DaggerContextComponent.builder()
            .contextModule(ContextModule(this.applicationContext))
            .build()

  }

  companion object {
    lateinit var contextComponent: ContextComponent
  }

}