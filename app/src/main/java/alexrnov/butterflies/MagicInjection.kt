package alexrnov.butterflies

import dagger.Component
import dagger.Module
import dagger.Provides

/**
// that tells Dagger 2 this is where we stores the @Provides functions within.
@Module
class MagicModule {

  // any name, but usually names provides<ClassName>
  @Provides
  fun providesInfo(): MagicInfo {
    return MagicInfo("Love dagger")
  }
}

// @Inject - get the Info class instantiated as needed through the MagicBox.
class MagicInfo(val text: String) {
  //val text = "Hello Dagger 2"
}

// <Something> Component
@Component(modules = [MagicModule::class])
interface MagicComponent {
  // tell that it is there to perform on MainActivity (may be any name, usually inject)
  fun inject(app: MainActivity)
}
        */