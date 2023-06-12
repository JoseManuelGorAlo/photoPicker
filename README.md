# photoPicker
The "Prueba6PhotoPicker" project is an Android application that provides users with the ability to select and display images from their device's photo gallery or take photos using the camera. The application has been developed using the ImagePicker and Dexter .
Key Features:

Image Selection: Users can browse their photo gallery and select a desired image. When clicking on the 'Gallery' button, they will be prompted to grant permissions to access external storage. Once the permissions are granted, the gallery will open, and they can select an image. The name of the selected image will be displayed in a success message.

Photo Capture: Users also have the option to capture a photo using the device's camera. When clicking on the 'Camera' button, they will be prompted to grant permissions to access the camera. After the permissions are granted, the camera application will open, and they can take a photo. The captured image will be displayed in the user interface.

Image Display: The selected or captured images are shown in the user interface using a series of ImageViews. In this project, a total of five ImageViews have been used to display up to five images. Each ImageView is identified using a variable in the code and is updated with the corresponding image when an image is selected or captured.

Permission Management: The Dexter library is used to request and manage the necessary permissions to access the gallery and camera. This ensures that the application complies with permission requirements and provides a smooth user experience by requesting permissions when needed.

The application is designed to be user-friendly and provide users with a convenient way to select images from the gallery or take photos with the device's camera. The source code is provided to allow other developers to understand and utilize these functionalities in their own Android projects.
