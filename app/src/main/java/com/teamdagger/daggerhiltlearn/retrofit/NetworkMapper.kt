package com.teamdagger.daggerhiltlearn.retrofit

import com.teamdagger.daggerhiltlearn.model.Blog
import com.teamdagger.daggerhiltlearn.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<BlogDataEntity, Blog>{
    override fun mapFromEntity(entity: BlogDataEntity): Blog {
        return Blog(
            entity.id,
            entity.title,
            entity.body,
            entity.image,
            entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogDataEntity {
        return BlogDataEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.image,
            domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogDataEntity>): List<Blog>{
        return entities.map{mapFromEntity(it)}
    }

}
