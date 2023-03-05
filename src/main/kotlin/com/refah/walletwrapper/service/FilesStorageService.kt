package com.refah.walletwrapper.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FilesStorageService {
    private final val root: Path = Paths.get("uploads")

    init {
        try {
            Files.createDirectories(root)
        } catch (e: IOException) {
            throw RuntimeException("Could not initialize folder for upload!")
        }
    }

    @Async
    fun save(file: MultipartFile) {
        try {
            file.originalFilename?.let { this.root.resolve(it) }?.let { Files.copy(file.inputStream, it) }
        } catch (e: Exception) {
            if (e is FileAlreadyExistsException) {
                throw RuntimeException("A file of that name already exists.")
            }
            throw RuntimeException(e.message)
        }
    }
}