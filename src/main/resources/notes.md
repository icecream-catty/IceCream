Main
- application( exitProcessOnExit = true)
  - when window close terminal will stop running in background
- () -> Unit
  - doesnt give back anything
  - doesnt take anything bc nothing in ()
- (Int) -> Int
  - take smt and return smt (specifically int)
  - Ex: 
    - fun addOneParam(parameter: Int): Int {
      return parameter + 1
      }
    - OR
    - fun addOneParam(parameter: Int): Int = parameter + 1
      - can delete return Int if its obvious that its an Int (only for = not for {})
    - fun Int.addOneReceiver(): Int {
      return this + 1
      }
    - 5.addOneReceiver()
      - used as like a definition and u can call upon it again
    - 5.addOneReceiver() == addOneParam(5)
      6.addOneReceiver() == addOneParam(6)
- @Composable
  - says it is a UI function (user interface - everything u see on screen)
- For something to change by input and not by smt in the code:
  - var name by remember { mutableStateOf("Cat") }
  - TextField(value = name, onValueChange = { name = it })
- $ : variable inside a string

Home.kt
- app1 naming TextField function usage
  - naming a event starting off with Untitled then changing as user types in title
  - 