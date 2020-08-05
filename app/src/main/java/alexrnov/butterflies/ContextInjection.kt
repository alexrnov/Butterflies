package alexrnov.butterflies

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

  @Singleton
  @Provides
  fun providesContext(): Context {
    return context
  }
}

// makes Dagger create a graph of dependencies
@Singleton
@Component(modules = [ContextModule::class])
interface ContextComponent {
  // the return type of functions inside the component interface is what can be consumed from the graph.
  fun inject(): Context
}