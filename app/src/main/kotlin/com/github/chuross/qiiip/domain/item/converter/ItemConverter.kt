package com.github.chuross.qiiip.domain.item.converter

import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.item.ItemIdentity
import com.github.chuross.qiiip.infrastructure.qiita.resource.Item as ResourceItem

class ItemConverter {

    private constructor()

    companion object {
        fun convertToModel(resource: ResourceItem): Item {
            System.err.println(resource.id!!)
            val item = Item(ItemIdentity(resource.id!!))
            item.metaInfo = ItemMetaConverter.convertToModel(resource)
            return item
        }

        fun convertToModels(resources: MutableList<ResourceItem>): List<Item> {
            return resources.map { resource -> ItemConverter.convertToModel(resource) }
        }
    }

}