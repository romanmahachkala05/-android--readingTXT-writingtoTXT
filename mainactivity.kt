	
	
	data class dclass1(val deviceName: String = "", val scriptText: String = "")

        //i thought its ok
        var dclasslist1 = listOf<dclass1>()
        var dclasslist2 = listOf<dclass1>()
        var dclasslist3 = listOf<dclass1>()
        var dclasslistArray = arrayOf(dclasslist1, dclasslist2, dclasslist3)
        var dclasscount = 0
        var dcclassnamescount = 0
        var devicenamehelp = ""
        var scripttexthelp = listOf<String>()
        var dclassnames = listOf<String>()
        val sdcard = Environment.getExternalStorageDirectory()
        loadbands.setOnClickListener {
            try {
                var txtfile = File(sdcard.absolutePath,"bands.txt") if (txtfile.exists()) {
                    try {
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
                                devicenamehelp = txtfileList.get(i)
                                scripttexthelp = emptyList()
                                i++
                                //fill deviceName with scriptText
                                while (!txtfileList.get(i).startsWith("$")) {
                                    scripttexthelp += txtfileList.get(i)
                                    i++
                                }
                                dclasslistArray[dclasscount] = dclasslistArray[dclasscount] + dclass1(devicenamehelp.toString(), scripttexthelp.toString().replace(",","").replace(" ","\n"))

                            }
                            i++
                        }
                        //
                        // textViewCurrentDevice.text = dclasslistArray[0].get(0).deviceName
                        textViewCurrentDevice.text = dclasslistArray[1].get(1).scriptText.toString()
                      //

                    } catch (e: IOException) {
                        Toast.makeText(this, "Что то пошло не так :(", Toast.LENGTH_SHORT).show()

                    }

                }




            }
            catch (ioe: IOException) {
                Toast.makeText(this, "не считался файл", Toast.LENGTH_SHORT).show()
            }
        }





        unloadbands.setOnClickListener {
            try {
                var file = File(sdcard.absolutePath, "script.txt")

                val osw = OutputStreamWriter(FileOutputStream(file))
		
		//underconstruction
                osw.write(dclasslistArray[1].get(1).scriptText.replace("[","").replace("]",""))
                osw.flush()
                osw.close()
                Toast.makeText(this, "Script created", Toast.LENGTH_LONG).show()



            }
            catch (ioe: IOException) {
                Toast.makeText(this, "не считался файл", Toast.LENGTH_SHORT).show()
            }
        }
