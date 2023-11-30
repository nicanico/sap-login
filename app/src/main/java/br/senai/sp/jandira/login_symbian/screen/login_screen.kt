//package br.senai.sp.jandira.login_symbian.screen
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material3.Card
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FilledIconButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.LifecycleCoroutineScope
//import br.senai.sp.jandira.login_symbian.ApiService
//import br.senai.sp.jandira.login_symbian.R
//import br.senai.sp.jandira.login_symbian.RetrofitHelper
//import coil.compose.rememberAsyncImagePainter
//import coil.request.ImageRequest
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
//import com.google.firebase.storage.storage
//import com.google.gson.JsonObject
//import androidx.lifecycle.LifecycleCoroutineScope
//import androidx.lifecycle.lifecycleScope
//
//class login_screen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//
//                ) {
//                    LoginScreen(lifecycleScope)
//                }
//
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginScreen(lifecycleCoroutineScope: LifecycleCoroutineScope) {
//
//  var userEmailState by remember {
//    mutableStateOf("")
//  }
//
//    var userPasswordState by remember {
//        mutableStateOf("")
//    }
//
//    var context = LocalContext.current
//
//    // Obter foto da galeira de imagens
//    // tipamos nossa mutableStateOf no tipo uri
//    var photoUri by remember{
//        mutableStateOf<Uri?>(null)
//    }
//
//
//
//    // Criar o objeto que abrirá a galeria e retornará
//    // a Uri da imagem selecionada
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ){
//        photoUri = it
//    }
//
//    // passamos a uri, ele busca e reconstroi a imagem
//    var painter = rememberAsyncImagePainter(
//        ImageRequest.Builder(LocalContext.current).data(photoUri).build()
//    )
//
//    Column (
//        modifier = Modifier
//            .background(Color.Black)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Column {
//            Image(
//                painter = painterResource(id = R.drawable.logoapp),
//                contentDescription = "image description",
//                contentScale = ContentScale.None
//            )
//        }
//
//        Column (
//            modifier = Modifier.width(350.dp).height(350.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            //
//            Box(
//                contentAlignment = Alignment
//                    .BottomEnd, modifier = Modifier.padding(top = 25.dp)
//
//            ) {
//                Card(
//                    modifier = Modifier
//                        .size(100.dp)
//                        .align(alignment = Alignment.TopEnd),
//                    shape = CircleShape,
//                    border = BorderStroke(
//                        width = 1.dp,
//                        Brush.horizontalGradient(
//                            colors = listOf(Color(0xFF7339E5), Color.Transparent))),
//                ) {
//                    Image(
//                        painter = painter,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .align(Alignment.End)
//                            .size(320.dp)
//                            .padding(4.dp),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//                Image(
//                    painter = painterResource(id = R.drawable.camera_foreground),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .offset(x = 0.dp, y = (5).dp)
//                        .size(28.dp)
//                        .clickable {
//                            launcher.launch("image/*")
//                        },
//                    colorFilter = ColorFilter.tint(Color(0xFF7339E5))
//
//                )
//            }
//
//            //
//
//            Column (
//
//            ) {
//                Text(
//                    text = "Email address",
//                    color = Color(0xFF575758)
//                )
//                OutlinedTextField(
//                    value = userEmailState,
//                    onValueChange = {
//                        userEmailState = it
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                )
//            }
//
//            Spacer(modifier = Modifier.width(408.dp))
//
//            Column {
//                Text(
//                    text = "Password",
//                    color = Color(0xFF575758)
//                )
//                OutlinedTextField(
//                    value = userPasswordState,
//                    onValueChange = {
//                        userPasswordState = it
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                )
//            }
//
//            Spacer(modifier = Modifier.width(408.dp))
//
//            FilledIconButton(
//                modifier = Modifier
//                    .width(325.dp)
//                    .height(45.dp),
//                onClick = { photoUri?.let { uploadImage(it, context, userEmailState, userPasswordState) } }
//            ) {
//                Text("Sign In")
//            }
//
//        }
//    }
//}
//
//
//private fun uploadImage(imageUri: Uri, context: Context, emailUser: String, password: String) {
//    //Referencia para acesso e manipulação do cloud Storage e firestore
//    lateinit var storageRef: StorageReference
//    lateinit var fibaseFirestore: FirebaseFirestore
//    lateinit var apiService: ApiService
//
//    storageRef = FirebaseStorage.getInstance().reference.child("images")
//    fibaseFirestore = FirebaseFirestore.getInstance()
//
//    storageRef = storageRef.child(System.currentTimeMillis().toString())
//    apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
//
//    imageUri?.let {
//        storageRef.putFile(it).addOnCompleteListener { task ->
//
//            if (task.isSuccessful) {
//
//                storageRef.downloadUrl.addOnSuccessListener { uri ->
//
//                    val map = HashMap<String, Any>()
//                    map["pic"] = uri.toString()
//
//                    fibaseFirestore.collection("images").add(map)
//                        .addOnCompleteListener { firestoreTask ->
//
//                            if (firestoreTask.isSuccessful) {
//
//                                lifecycleCoroutineScope.launch {
//                                    val body = JsonObject().apply {
//                                        addProperty("image", uri.toString())
//                                        addProperty("email", emailUser)
//                                        addProperty("senha", password)
//                                    }
//
//                                    val result = apiService.createUser(body)
//
//                                    if(result.isSuccessful){
//                                        val msg = result.body()?.get("mensagemStatus")
//                                        Log.e("CREATE-USER", "STATUS: ${msg}")
//                                        Toast.makeText(
//                                            context,
//                                            "Usuario criado com sucesso ${uri}",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }else {
//                                        Log.e("CREATE-CATEGORY", "STATUS: ${result.message()}")
//                                    }
//                                }
//
//                            } else {
//                                Toast.makeText(
//                                    context,
//                                    "Erro ao tentar realizar o upload.",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//
//                            }
//
//                        }
//                }
//
//            } else {
//                Toast.makeText(context, "Erro ao tentar realizar o upload.", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//    }}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//        LoginScreen()
//
//}