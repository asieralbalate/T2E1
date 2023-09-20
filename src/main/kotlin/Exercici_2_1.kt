package exercicis

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.StringBuilder

fun main() {
    val filePath = "Penyagolosa.bmp"
    val f = File(filePath)
    val fi = FitxerImatge(f)
    fi.transformaNegatiu()
    fi.transformaObscur()
    fi.transformaBlancNegre()
}

class FitxerImatge(fEnt: File) {
    var f: File = File("")   // No modifiqueu aquesta línia. El seu valor s'ha de modificar en el constructor
    lateinit var newImage : File

    init {
        if (fEnt.exists() && fEnt.extension == "bmp") {
            this.f = fEnt
        } else {
            if (!fEnt.exists()) {
                println("Error: No existeix l'archiu")
            } else {
                println("Error: L'extensio no es correcta")
            }
        }
        // Constructor
        // Control d'existència del fitxer i control de l'extensió .bmp (traure missatges d'error)
        // En cas que tot siga correcte, inicialitzar f amb fEnt
    }

    fun transformaNegatiu() {
        val oldName = f.name
        val newName = StringBuilder()
        for (letter in oldName){
            if (letter == '.') {
                newName.append("_n.")
            } else {
                newName.append(letter)
            }
        }

        this.newImage = File("$newName")
        val input = FileInputStream(this.f)
        val output = FileOutputStream(this.newImage)
        var imageByte: Int = input.read()
        var counter = 0
        while (imageByte != -1){
            if (counter < 55) {
                output.write(imageByte)
            } else {
                val newImageByte = 255 - imageByte
                output.write(newImageByte)
            }
            imageByte = input.read()
            counter++
        }
        input.close()
        output.close()
        // Transformar a negatiiu i guardar en _n.bmp
    }


    fun transformaObscur() {
        val oldName = f.name
        val newName = StringBuilder()
        for (letter in oldName){
            if (letter == '.') {
                newName.append("_o.")
            } else {
                newName.append(letter)
            }
        }

        this.newImage = File("$newName")
        val input = FileInputStream(this.f)
        val output = FileOutputStream(this.newImage)
        var imageByte = input.read()
        var counter = 0
        while (imageByte != -1){
            if (counter < 55) {
                output.write(imageByte)
            } else {
                val newImageByte = imageByte / 2
                output.write(newImageByte)
            }
            imageByte = input.read()
            counter++
        }
        input.close()
        output.close()
        // Transformar a una imatge més fosca i guardar en _o.bmp
    }


    fun transformaBlancNegre() {
        val oldName = f.name
        val newName = StringBuilder()
        for (letter in oldName){
            if (letter == '.') {
                newName.append("_bn.")
            } else {
                newName.append(letter)
            }
        }
        this.newImage = File("$newName")
        val input = FileInputStream(this.f)
        val output = FileOutputStream(this.newImage)
        var imageByte = input.read()
        var counter = 0
        while (imageByte != -1){
            if (counter < 55) {
                output.write(imageByte)
            } else {
                val r = imageByte
                val g = input.read()
                val b = input.read()

                val grey = ((r+g+b)/3)

                output.write(grey)
                output.write(grey)
                output.write(grey)
            }
            imageByte = input.read()
            counter++
        }
        input.close()
        output.close()
        // Transformar a una imatge en blanc i negre i guardar en _bn.bmp
    }
}