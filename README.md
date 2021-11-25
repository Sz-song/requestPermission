# requestPermission


## Basic Usage

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
    })
```


## 当权限被拒绝一次时，我们通常需要解释为啥需要这个权限，所以需要添加一个解释弹框，告诉用户我们使用该权限的原因。

使用 addBeforeDialog()添加默认弹框

如果需要自定义弹框类型可以自定义弹框集成
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
    })
```
or

```kotlin
RequestPermission(this)
    .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
    .addBeforeDialog(MyBeforeDialog(this))    
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
    })
```
