# **User Manual for Bookmarker App**

## **Introduction**
The **Bookmarker** app is an Android-based application that allows users to manage their bookmarks easily. It supports adding, viewing, editing, and deleting bookmarks. The app also includes features like a splash screen, bookmark details view, filter by category and an About dialog.

---

## **Features**
- **Add Bookmark**: Allows users to add new bookmarks with a title, URL, description, category, and date.
- **Bookmark List**: Displays a list of saved bookmarks using a `ListView`, where each item is clickable to view more details or can be long-pressed to edit or delete.
- **Bookmark Details**: Users can view detailed information about a specific bookmark.
- **Splash Screen**: The app displays a splash screen when the app is launched, briefly showing the app's logo or branding.
- **About Dialog**: Displays information about the app, like the app version and credits.
- **Filter Option**: Displays the list based on the selected category.

---

## **App Workflow**
The app workflow involves the following steps:

1. **Splash Screen**:
   - When the app is opened, a splash screen is displayed briefly before proceeding to the main activity.
   - If the splash screen feature is disabled, the app directly launches the main activity.

2. **Main Activity**:
   - The app shows a list of saved bookmarks.
   - Each bookmark in the list can be clicked to view more details or long-pressed to edit or delete.
   
3. **Adding a Bookmark**:
   - Users can click the "Add Bookmark" button to open the Add Bookmark activity, where they can enter the details of the bookmark: title, URL, description, category, and date.
   - Once the user saves the bookmark, it appears in the bookmark list.

4. **Bookmark Details**:
   - Clicking on a bookmark opens a dialog showing its detailed information.
   - The URL in the details can be clicked to open it in a browser.

5. **Editing and Deleting Bookmarks**:
   - Users can edit or delete bookmarks by long-pressing an item in the bookmark list.

6. **About Dialog**:
   - Users can access the About dialog, which displays information about the app, such as its version and credits.
  
7. **Filter Option**:
   - Users can filter the bookmarks based on the category (Social Media, Entertainment, Games and Programming).  
   
---

## **User Interface**
- **Bookmark List**: Displays the saved bookmarks.
  - Each bookmark includes its title, URL, description, and date.
  - You can long-press to delete a bookmark.

- **Add Bookmark Activity**: A screen for entering new bookmark details.
  - Fields to be filled out: Title, URL, Description, Category, Date.

- **Bookmark Details Dialog**: A popup displaying full details of a selected bookmark.
  - Shows Title, URL (clickable), Description, Category, and Date.
 
- **About Dialog**: A popup displaying the app details.
  - Display the logo, version and developers.

- **Edit Bookmark Activity**: A screen for edit the bookmark details.
  - Fields to be filled out: Title, URL, Description, Category, Date.
    
---

## **How to Use the App**
1. **Launch the App**: After launching, the splash screen is displayed briefly, followed by the main activity showing a list of your saved bookmarks.
   
2. **Add a Bookmark**: 
   - Tap on the "Add Bookmark" button.
   - Fill in the details of your bookmark and save it.
  
3. **Clear All**: 
   - It will clear all the bookmark(s).
   
4. **View Bookmark Details**: 
   - Tap any bookmark in the list to open the bookmark details dialog.
   - Click the URL to open it in your browser.
   
5. **Edit or Delete Bookmarks**:
   - Tap on the Edit or Delete button beside the bookmark to either edit or delete the bookmark.

6. **Access About Dialog**:
   - Tap the "About" option from the menu to view the app's information.
  
7. **Enable Splash Screen**: 
   - To enable or disable the splash screen.

8. **Filter Option**: 
   - To filter the bookmark by category.

---

## **File Structure**

```
Bookmarker/
├── app/
│   ├── src/
│   │   ├── com/
│   │   │   ├── example/
│   │   │   │   ├── bookmarker/
│   │   │   │   │   ├── AboutDialogFragment.kt
│   │   │   │   │   ├── AddBookmarkActivity.kt
│   │   │   │   │   ├── Bookmark.kt
│   │   │   │   │   ├── BookmarkAdapter.kt
│   │   │   │   │   ├── BookmarkDetailDialogFragment.kt
│   │   │   │   │   ├── MainActivity.kt
│   │   │   │   │   ├── PrefManager.kt
│   │   │   │   │   └── Splash.kt
│   ├── res/
│   │   ├── drawable/
│   │   │   ├── add_bookmark_btn
│   │   │   ├── bg_circle.xml
│   │   │   ├── et_bg.xml
│   │   │   ├── ic_back.xml
│   │   │   ├── ic_delete.xml
│   │   │   ├── ic_edit.xml
│   │   │   ├── ic_launcher_background.xml
│   │   │   ├── ic_launcher_foreground.xml
│   │   │   ├── ic_no_data.xml
│   │   │   └── logo.png
│   │   ├── font/
│   │   │   └── andada_pro.xml
│   │   ├── layout/
│   │   │   ├── activity_add_bookmark.xml
│   │   │   ├── activity_main.xml
│   │   │   ├── activity_splash.xml
│   │   │   ├── bookmark_item.xml
│   │   │   ├── dialog_title.xml
│   │   │   ├── fragment_about_dialog.xml
│   │   │   └── fragment_bookmark_detail_dialog.xml
│   │   ├── menu/
│   │   │   └── menu.xml
│   │   └── values/
│   │       ├── colors.xml
│   │   │   ├── font_certs.xml
│   │   │   ├── ic_launcher_background.xml
│   │   │   ├── strings.xml
│   │   │   └── themes.xml

└── Gradle Scripts
```

## Libraries Used
- **SharedPreferences**: To store the bookmarks persistently.
- **AlertDialog**: For showing dialogs to edit or view bookmark details.
- **ListView**: To display a list of saved bookmarks.
- **Gradle**: For building the project and managing dependencies.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yawnyong/Bookmarker.git
   ```

2. Open the project in Android Studio.

3. Run the app on an emulator or a physical device (Must be Android OS).

## Screenshots
#### Splash Screen and Main Screen
<p align="center">
  <kbd>
    <img src="https://github.com/user-attachments/assets/7c0c76ea-3754-4579-bb30-517d775fea62" width="200"/><br>
    <b>Splash Screen</b>
  </kbd>

  <kbd>
    <img src="https://github.com/user-attachments/assets/9bc2a97c-9c5c-4e66-a7d6-121475a106f3" width="200"/><br>
    <b>Main Screen (Empty Bookmark List)</b>
  </kbd>

 <kbd>
    <img src="https://github.com/user-attachments/assets/f79df6ee-9cd2-4bfb-87b8-36b6723ed273" width="200"/><br>
    <b>Add Bookmark Button</b>
  </kbd>
  
  <kbd>
    <img src="https://github.com/user-attachments/assets/48813a52-3a17-448f-b083-f829ce281f80" width="200"/><br>
    <b>Add Bookmark</b>
  </kbd>
</p>

#### Add Bookmark Screen
<p align="center">
  <kbd>
    <img src="https://github.com/user-attachments/assets/e2f27823-6c2b-42c7-8a6a-be0445ac3552" width="200"/><br>
    <b>Validation Alert</b>
  </kbd>

  <kbd>
    <img src="https://github.com/user-attachments/assets/c27b0a2d-e6de-4799-afa8-5532d15751ee" width="200"/><br>
    <b>Invalid URL Format Alert</b>
  </kbd>

 <kbd>
    <img src="https://github.com/user-attachments/assets/de6ef5a8-9484-44f6-9fc0-2c27bef47d3d" width="200"/><br>
    <b>Add Bookmark Confirmation Alert</b>
  </kbd>

  <kbd>
    <img src="https://github.com/user-attachments/assets/b9b54ae2-0910-4a99-bead-09ad20e26eae" width="200"/><br>
    <b>Added Success Alert</b>
  </kbd>
</p>

#### Bookmark List and Bookmark Details Dialog with clickable URL
<p align="center">
  <kbd>
    <img src="https://github.com/user-attachments/assets/0b4d5319-0734-4ab1-9758-5e48a7735868" width="200"/><br>
    <b>Bookmark List (Added Bookmark)</b>
  </kbd>

  <kbd>
    <img src="https://github.com/user-attachments/assets/f488c4fd-457a-440a-b54b-327977c7d5c3" width="200"/><br>
    <b>Bookmark Details Dialog</b>
  </kbd>

 <kbd>
    <img src="https://github.com/user-attachments/assets/99ba1d54-c888-41ac-924e-10e924928ebc" width="200"/><br>
    <b>Clickable url in the Bookmark Detail Dialog</b>
  </kbd>

</p>

#### Delete Bookmark Options
<p align="center">
  <kbd>
    <img src="https://github.com/user-attachments/assets/c454298a-8ef1-42e0-949e-92d0a9aca8f0" width="200"/><br>
    <b>Delete Button</b>
  </kbd>

  <kbd>
    <img src="https://github.com/user-attachments/assets/155de358-d0cb-44df-9b44-e491184dfbbb" width="200"/><br>
    <b>Long Press Delete Function</b>
  </kbd>

 <kbd>
    <img src="https://github.com/user-attachments/assets/6ae18e3a-a964-475d-a597-f0a7f4372890" width="200"/><br>
    <b>Delete Confirmation</b>
  </kbd>
  
</p>

#### Edit Bookmark Option
<p align="center">
  <kbd>
    <img src="https://github.com/user-attachments/assets/4cba78a5-d52c-4152-b300-b8b8bc107b4a" width="200"/><br>
    <b>Edit Button</b>
  </kbd>
  
  <kbd>
    <img src="https://github.com/user-attachments/assets/1609bf20-7e0b-490e-92d5-d40a7b9c5442" width="200"/><br>
    <b>Edit Page</b>
  </kbd>

 <kbd>
    <img src="https://github.com/user-attachments/assets/06f54aa4-fe43-4470-bc9c-25e6d00c8511" width="200"/><br>
    <b>Edit Confirmation Confirmation</b>
  </kbd>

   <kbd>
    <img src="https://github.com/user-attachments/assets/e3afe49d-3490-4340-a4b5-3e705017722b" width="200"/><br>
    <b>Edit Successful Alert</b>
  </kbd>

</p>

#### Menu Item Options & About Dialog
<p align="center">
  <kbd>
    <img src="https://github.com/user-attachments/assets/e3003278-66ae-426c-924e-72bdede98c57" width="200"/><br>
    <b>Menu Item Options</b>
  </kbd>

  <kbd>
    <img src="https://github.com/user-attachments/assets/c53de2f5-3a1c-429e-8f75-3b4e41ad670b" width="200"/><br>
    <b>About Dialog</b>
  </kbd>

</p>

## Group Members:
- **Tan Zhao Yong** (0207816)
- **Kuberan Suppiah** (0208385)

## Contributing
Feel free to fork this repository, contribute improvements, or report issues via GitHub issues.

## License
This project is open source under the MIT License. See the [LICENSE](LICENSE) file for more information.
