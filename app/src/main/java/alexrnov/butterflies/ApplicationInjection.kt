package alexrnov.butterflies

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton

// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule(private val context: Context) {

  // @Singleton - Way to scope types inside a Dagger Module
  // @Provides tell Dagger how to create instances of the type that this function
  // returns (i.e. Context). Function parameters are the dependencies of this type.
  @Singleton
  @Provides
  fun provideContext(): Context {
    return context
  }
}

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [LoginComponent::class])
class SubcomponentsModule {}

// @Component makes Dagger create a graph of dependencies
// Dagger can create a graph of the dependencies in your project that it can use
// to find out where it should get those dependencies when they are needed. To make
// Dagger do this, you need to create an interface and annotate it with @Component.
// Dagger creates a container as you would have done with manual dependency injection.

// Scope annotations on a @Component interface informs Dagger that classes annotated
// with this annotation (i.e. @Singleton) are bound to the life of the graph and so
// the same instance of that type is provided every time the type is requested.

// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
// @Singleton - You can use it to annotate ApplicationComponent and the objects you want to reuse across the whole application.
// Because ApplicationComponent is created when the app is launched (in the application class), it is destroyed when the app gets
// destroyed. Thus, the unique instance of UserRepository always remains in memory until the application is destroyed.

// Including SubcomponentsModule, tell ApplicationComponent that
// LoginComponent is its subcomponent.
@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface ApplicationComponent {
  // This tells Dagger that LoginActivity requests injection so the graph needs to
  // satisfy all the dependencies of the fields that LoginActivity is requesting.

  // Note that ApplicationComponent doesn't need to inject LoginActivity
  // anymore because that responsibility now belongs to LoginComponent, so you can remove the inject() method from ApplicationComponent.

  // Consumers of ApplicationComponent need to know how to create instances of
  // LoginComponent. The parent component must add a method in its interface to
  // let consumers create instances of the subcomponent out of an instance of the parent component:

  // Expose the factory that creates instances of LoginComponentin the interface:
  // This function exposes the LoginComponent Factory out of the graph so consumers
  // can use it to obtain new instances of LoginComponent
  fun loginComponent(): LoginComponent.Factory
}

// To scope LoginViewModel to the lifecycle of LoginActivity you need to create
// a new component (a new subgraph) for the login flow and a new scope.
// @Subcomponent annotation informs Dagger this interface is a Dagger Subcomponent
// Note that you cannot use the @Singleton annotation because it's already been used
// by the parent component and that'd make the object an application singleton (unique instance for the whole app).
@Subcomponent
interface LoginComponent {

  // You also must define a subcomponent factory inside LoginComponent so
  // that ApplicationComponent knows how to create instances of LoginComponent.
  // Factory that is used to create instances of this subcomponent
  @Subcomponent.Factory
  interface Factory {
    fun create(): LoginComponent
  }

  // This tells Dagger that LoginActivity requests injection from LoginComponent
  // so that this subcomponent graph needs to satisfy all the dependencies of the
  // fields that LoginActivity is injecting

  // If you have multiple classes that request injection, you have to specifically
  // declare them all in the component with their exact type

  // MainActivity and ContentFragment request injection from LoginComponent.
  // The graph needs to satisfy all the dependencies of the fields those classes are injecting

  fun inject(activity: MainActivity)
  fun inject(contentFragment: ContentFragment)
}

class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository
): ViewModel() {

  private val pageIndex = MutableLiveData<Int>()

  // will run only when the returned LiveData is observed. map - Returns a LiveData mapped from
  // the input source LiveData by applying mapFunction to each value set on source.
  val items = Transformations.map<Int, List<String>>(pageIndex) { index: Int ->
    userRepository.loadList(index)
  }

  fun setIndex(index: Int) {
    pageIndex.value = index
  }

}

// @Inject lets Dagger know how to create instances of this object
// // Scope this class to a component using @Singleton scope (i.e. ApplicationGraph)
@Singleton
class UserRepository @Inject constructor(val context: Context) {

  /** invoke from ViewModel class */
  fun loadList(pageIndex: Int): List<String> {

    //context = Initialization.contextComponent.inject()

    Log.i("P", "con repository = " + context.packageName + " page index = " + pageIndex)

    val index = pageIndex + 1
    val input = context.assets.open("tab$index/item1/description.txt")


    val bf: BufferedReader
    val result = StringBuilder()
    try {
      bf = BufferedReader(InputStreamReader(input))
      var line = bf.readLine()
      while (line != null) {
        result.append(line)
        result.append(System.getProperty("line.separator"))
        line = bf.readLine()
      }
      Log.i("P", "result = " + result.toString())
    } catch (e: IOException) {
      //e.printStackTrace();
    }


    val list: MutableList<String> = ArrayList()

    if (pageIndex == 0) {
      list.add("0")
      list.add("1")
      list.add("2")
      list.add("3")
      list.add("4")
      list.add("5")
      list.add("6")
    } else if (pageIndex == 1) {
      list.add("7")
      list.add("8")
      list.add("9")
      list.add("10")
      list.add("11")
      list.add("12")
      list.add("13")
    } else if (pageIndex == 2) {
      list.add("14")
      list.add("15")
      list.add("16")
      list.add("17")
      list.add("18")
      list.add("19")
      list.add("20")
    } else if (pageIndex == 3) {
      list.add("21")
      list.add("22")
      list.add("23")
      list.add("24")
      list.add("25")
      list.add("26")
      list.add("27")
    } else if (pageIndex == 4) {
      list.add("28")
      list.add("29")
      list.add("30")
      list.add("31")
      list.add("32")
      list.add("33")
      list.add("34")
    } else if (pageIndex == 5) {
      list.add("35")
      list.add("36")
      list.add("37")
      list.add("38")
      list.add("39")
      list.add("40")
      list.add("41")
    } else if (pageIndex == 6) {
      list.add("42")
      list.add("43")
      list.add("44")
      list.add("45")
      list.add("46")
      list.add("47")
      list.add("48")
    } else {
      list.add("49")
      list.add("50")
      list.add("51")
      list.add("52")
      list.add("53")
      list.add("54")
      list.add("55")
    }
    return list
  }

}
