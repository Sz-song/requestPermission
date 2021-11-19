# requestPermission


## Basic Usage

```kotlin
    /**
    * @param this  FragmentActivity or Fragment
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
