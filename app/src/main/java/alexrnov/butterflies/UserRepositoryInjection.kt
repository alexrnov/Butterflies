package alexrnov.butterflies

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

/**
// @Inject lets Dagger know how to create instances of this object
// // Scope this class to a component using @Singleton scope (i.e. ApplicationGraph)
@Singleton
class UserRepository @Inject constructor(
        val s1: Class1,
        val s2: Class2
) {

}

// If you annotate the other classes too, Dagger knows how to create them:
class Class1 @Inject constructor() {
  val s = "string Class1"
}

class Class2 @Inject constructor() {
  val s = "string Class2"
}

// @Component makes Dagger create a graph of dependencies
// Dagger can create a graph of the dependencies in your project that it can use
// to find out where it should get those dependencies when they are needed. To make
// Dagger do this, you need to create an interface and annotate it with @Component.
// Dagger creates a container as you would have done with manual dependency injection.

// Scope annotations on a @Component interface informs Dagger that classes annotated
// with this annotation (i.e. @Singleton) are bound to the life of the graph and so
// the same instance of that type is provided every time the type is requested.
@Singleton
@Component
interface RepositoryComponent {
  // The return type of functions inside the component interface is
  // what can be provided from the container
  // activity: MainActivity - This tells Dagger that MainActivity requests injection so
  // the graph needs to satisfy all the dependencies of the fields that LoginActivity is requesting.
  fun repository(): UserRepository
}

*/