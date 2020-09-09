	
	
	data class dclass1(val headerSmall: String = "", val headerSmallText: String = "")

        val dclasslistArray: MutableList<List<dclass1>> = ArrayList()
        for (i in 0..9) dclasslistArray.add(ArrayList())
        var dclasscount = 0
        var dcclassnamescount = 0
        var headerSmallH = ""
        var headerSmallTextH = listOf<String>()
        var dclassnames = listOf<String>()
        val sdcard = Environment.getExternalStorageDirectory()
        readfile.setOnClickListener {
            try {
                var txtfile = File(sdcard.absolutePath,"filename.txt") 
		    if (txtfile.exists()) {
			var txtfileList = txtfile.readLines()
			var i = 0
			while (i < txtfileList.size){

				    //i need to separate big headers with %
			     if (txtfileList.get(i).startsWith("%")) {
				dclassnames += txtfileList.get(i).removePrefix("%")
				dcclassnamescount++
				if (dcclassnamescount > 1) dclasscount++
			     }

			     //separate smaller headers (deviceName in data class) with @
			     if (txtfileList.get(i).startsWith("@")) {
				  headerSmallH = txtfileList.get(i)
				  headerSmallTextH = emptyList()
				  i++
				  //fill headerSmall with headerSmallText
				  while (!txtfileList.get(i).startsWith("$")) {
					headerSmallTextH += txtfileList.get(i)
					i++
				  }
			      dclasslistArray[dclasscount] = dclasslistArray[dclasscount] + dclass1(headerSmallH.toString(), headerSmallTextH.toString().replace(",","").replace(" ","\n"))
			      }
			 i++      
                         }
                    } else Toast.makeText(this, "txt was not found", Toast.LENGTH_SHORT).show()
            	}  catch (ioe: IOException) {
           	Toast.makeText(this, "unable to read txt", Toast.LENGTH_SHORT).show()
                }
        }

        createfile.setOnClickListener {
            try {
                var file = File(sdcard.absolutePath, "file1.txt")
                val osw = OutputStreamWriter(FileOutputStream(file))
		
		//underconstruction
                osw.write(dclasslistArray[1].get(1).scriptText.replace("[","").replace("]",""))
                osw.flush()
                osw.close()
                Toast.makeText(this, "Script created", Toast.LENGTH_LONG).show()
            }
            catch (ioe: IOException) {
                Toast.makeText(this, "unable to create txt", Toast.LENGTH_SHORT).show()
            }
        }
