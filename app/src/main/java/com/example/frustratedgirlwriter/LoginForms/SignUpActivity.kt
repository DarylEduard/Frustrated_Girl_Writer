package com.example.frustratedgirlwriter.LoginForms
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frustratedgirlwriter.Helper.uiHelper
import com.example.frustratedgirlwriter.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Activity To Display
        setContentView(R.layout.activity_signup)

        register_Button.setOnClickListener {
            //performRegister()
            mStorageRef = FirebaseStorage.getInstance().getReference();
            uploadImageToFirebaseStorage()

        }

        login_textView_registration.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        upload_photo_button.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data!= null){
            uiHelper().showLogCat("${data.data.toString()}")

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            upload_photo_button.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun performRegister(){
        val email = email_editText_registration.text.toString()
        val password = password_editText_registration.text.toString()
        val username = username_editText_registration.text.toString()

        if (email.isEmpty() || password.isEmpty()){

            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener

                //else if
                Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
                uiHelper().showLogCat("Signup success!")
                uploadImageToFirebaseStorage()
                //uiHelper().currentUser_logout()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Sign up failed!", Toast.LENGTH_SHORT).show()
            }
    }

    private var mStorageRef: StorageReference? = null
    private fun uploadImageToFirebaseStorage(){

        if(selectedPhotoUri != null){
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()


            var ref = mStorageRef!!.child("/images/users/sample.png")
            ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, "File Uploaded", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{ p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { p0 ->
                    var progress:Double = (100.0 * p0.bytesTransferred / p0.totalByteCount)
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }
        }
//        if (selectedPhotoUri == null) return
//
//
//
//        val filename = UUID.randomUUID().toString()
//        val ref = FirebaseStorage.getInstance().reference.child("/images/users/$filename")
//
//        ref.putFile(selectedPhotoUri!!)
//            .addOnSuccessListener {
//                uiHelper().showLogCat("Successfully uploaded image: ${it.metadata?.path}")
//            }
//            .addOnFailureListener(){
//                uiHelper().showLogCat("Upload failed!! ${it.toString()}")
//            }

    }

}
