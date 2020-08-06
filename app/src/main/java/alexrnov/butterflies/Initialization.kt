package alexrnov.butterflies

import alexrnov.butterflies.model.ApplicationComponent
import alexrnov.butterflies.model.DaggerApplicationComponent
import alexrnov.butterflies.model.ApplicationModule
import android.app.Application

// contextComponent lives in the Application class to share its lifecycle
class Initialization : Application() {

  // Reference to the application graph that is used across the whole app
  lateinit var applicationComponent: ApplicationComponent

  override fun onCreate() {
    super.onCreate()

    // Reference to the application graph that is used across the whole app
    applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this.applicationContext))
            .build()
  }
}