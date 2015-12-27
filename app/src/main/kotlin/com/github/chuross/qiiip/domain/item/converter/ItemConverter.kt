package com.github.chuross.qiiip.domain.item.converter

import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.item.ItemIdentity
import com.github.chuross.qiiip.infrastructure.qiita.resource.Item as ResourceItem

class ItemConverter {

    private constructor()

    companion object {
        fun convertToModel(resource: ResourceItem): Item = Item(ItemIdentity(resource.id!!)).apply {
            metaInfo = ItemMetaConverter.convertToModel(resource)
        }

        fun convertToModels(resources: Collection<ResourceItem>): List<Item> {
            return resources.map { resource -> ItemConverter.convertToModel(resource) }
        }
    }

}