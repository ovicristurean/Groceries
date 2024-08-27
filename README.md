**Proof of concept Kotlin Multiplaform app for updating the design with themed data from a server, using a Gradle plugin**


You can call the `refreshTheme` task, which has a path argument to specify where you want your theme data to be saved, like this:
`./gradlew refreshTheme -PthemePath=com/ovidiucristurean/groceries/ui/theme`
This will download the `Color.kt`, `Theme.kt` and `Type.kt` files and place them at the specified path:

![Screenshot 2024-08-21 at 16 56 54](https://github.com/user-attachments/assets/f1b48f74-ed06-4ea9-bcea-bee4e9cdcc2d)


The app itself is a simple multiplatform project for a groceries list app for Android and iOS.

![Simulator Screenshot - iPhone 15 - 2024-08-27 at 09 51 24](https://github.com/user-attachments/assets/7c68b5bf-654f-43f3-a935-b7f2ba342417)
![Simulator Screenshot - iPhone 15 - 2024-08-27 at 09 52 00](https://github.com/user-attachments/assets/c5b9b4c4-a859-49f7-9649-50b23804924d)
![Simulator Screenshot - iPhone 15 - 2024-08-27 at 09 53 38](https://github.com/user-attachments/assets/4b88e5e0-bdf4-447b-b925-b1add3ffd7fc)
