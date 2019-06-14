package br.com.creditas.kotlinspringsample.repositories

import br.com.creditas.kotlinspringsample.models.Post
import br.com.creditas.kotlinspringsample.models.Tag
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TagRepository : CrudRepository<Tag, UUID>
