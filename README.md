# requestPermission


## Basic Usage

Step 1.Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
        implementation 'com.github.Sz-song:requestPermission:v1.0'
	}

[![](https://www.jitpack.io/v/Sz-song/requestPermission.svg)](https://www.jitpack.io/#Sz-song/requestPermission)



```kotlin
/**
 * @param activity
 * FragmentActivity or Fragment
**/
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .requestCallback(object : PermissionsCallback {
        override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
            if (allGranted) {
                Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
            }else{
                val stringBuffer=StringBuffer()
                deniedList.forEach{
                    stringBuffer.append(it)
                }
                Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }).request()
```


## 当权限被拒绝一次时，我们通常需要解释为啥需要这个权限，所以需要添加一个解释弹框，告诉用户我们使用该权限的原因。

使用 addBeforeDialog()添加默认弹框

如果需要自定义弹框类型可以自定义弹框并继承

    PermissionDialog类 

并实现一下两个方法，返回取消和确认按钮：  

    abstract fun getCancelView():View

    abstract fun getConfirmView():View

```kotlin
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .addBeforeDialog()    
    .requestCallback(object : PermissionsCallback {
        override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
            if (allGranted) {
                Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
            }else{
                val stringBuffer=StringBuffer()
                deniedList.forEach{
                    stringBuffer.append(it)
                }
                Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }).request()
```
or

```kotlin
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .addBeforeDialog(BeforeDialog(this))  //如果使用默认弹窗则不需要传入参数，使用自定义弹窗则传入自定义PermissionDialog() 的子类   
    .requestCallback(object : PermissionsCallback {
        override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
            if (allGranted) {
                Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
            }else{
                val stringBuffer=StringBuffer()
                deniedList.forEach{
                    stringBuffer.append(it)
                }
                Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }).request()
```




## 当权限被拒绝且不再询问时，我们通常需要弹框引导用户手动去设置页面开启权限。

使用 addDeniedDialog()添加默认引导弹框

如果需要自定义弹框类型可以自定义弹框并继承

    PermissionDialog类 

并实现一下两个方法，返回取消和确认按钮：

    abstract fun getCancelView():View

    abstract fun getConfirmView():View

```kotlin
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .addDeniedDialog()    
    .requestCallback(object : PermissionsCallback {
        override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
            if (allGranted) {
                Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
            }else{
                val stringBuffer=StringBuffer()
                deniedList.forEach{
                    stringBuffer.append(it)
                }
                Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }).request()
```
or

```kotlin
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .addDeniedDialog(DeniedDialog(this))//如果使用默认弹窗则不需要传入参数，使用自定义弹窗则传入自定义PermissionDialog() 的子类  
    .requestCallback(object : PermissionsCallback {
        override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
            if (allGranted) {
                Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
            }else{
                val stringBuffer=StringBuffer()
                deniedList.forEach{
                    stringBuffer.append(it)
                }
                Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }).request()
```


## 完整使用

```kotlin
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .addBeforeDialog(BeforeDialog(this))//如果使用默认弹窗则不需要传入参数，使用自定义弹窗则传入自定义PermissionDialog() 的子类
    .addDeniedDialog(DeniedDialog(this))//如果使用默认弹窗则不需要传入参数，使用自定义弹窗则传入自定义PermissionDialog() 的子类
    .requestCallback(object : PermissionsCallback {
        override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
            if (allGranted) {
                Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
            }else{
                val stringBuffer=StringBuffer()
                deniedList.forEach{
                    stringBuffer.append(it)
                }
                Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }).request()
```

##自定义弹窗的参考

```kotlin
class MyBeforeDialog (context: Context):PermissionDialog(context) {

    private lateinit var binding: DialogMyBeforeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogMyBeforeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.let {
            val param = it.attributes
            val width = (context.resources.displayMetrics.widthPixels * 0.8).toInt()
            val height = param.height
            it.setLayout(width, height)
            it.setBackgroundDrawable(ResourcesCompat.getDrawable(context.resources,R.color.transparent,null))
        }
    }

    override fun show() {
        super.show()
        if(permissionsList.isNotEmpty()&&getPermissionStr().isNotEmpty()){
            binding.permissions.text=getPermissionStr()
        }
    }
    
    /**实现该方法，并返回取消按钮**/
    override fun getCancelView(): View {
        return binding.cancel
    }

    /**实现该方法，并返回确认按钮**/
    override fun getConfirmView(): View {
        return binding.agree
    }

    
    private fun getPermissionStr() :String{
        val stringBuilder=StringBuffer()
        for (permission in permissionsList) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                val permissionGroup = permissionMapOnR[permission]
                if (permissionGroup != null ) {
                    val text = context.packageManager.getPermissionGroupInfo(permissionGroup, 0).loadLabel(context.packageManager)
                    if(!stringBuilder.contains(text)&&text.isNotEmpty()){
                        if(stringBuilder.isNotEmpty()){
                            stringBuilder.append("\n")
                            stringBuilder.append("- ")
                            stringBuilder.append(text)
                        }else{
                            stringBuilder.append("- ")
                            stringBuilder.append(text)
                        }
                    }
                }
            }
        }
        return stringBuilder.toString()
    }
}
```

##自定义弹窗的父类
```kotlin
abstract class PermissionDialog(context: Context):Dialog(context){
    /**本次请求的权限集合**/
    var permissionsList:Array<String> = arrayOf()
    
    /**被拒绝的权限集合**/
    var deniedList: MutableList<String> = mutableListOf()


    /**需要子类实现的方法**/
    abstract fun getCancelView():View
    abstract fun getConfirmView():View

}
```
