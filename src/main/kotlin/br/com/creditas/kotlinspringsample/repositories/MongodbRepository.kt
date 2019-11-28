package br.com.creditas.kotlinspringsample.repositories

import br.com.creditas.kotlinspringsample.models.MongoEntity
import java.util.UUID
import org.springframework.data.mongodb.repository.MongoRepository

interface MongodbRepository : MongoRepository<MongoEntity, UUID>
