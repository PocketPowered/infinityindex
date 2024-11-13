package com.wongislandd.infinityindex.pillars.characters.transformers

import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.util.DataWrapperTransformer

class CharacterTransformer : DataWrapperTransformer<NetworkCharacter, Character>() {
    override fun itemTransformer(input: NetworkCharacter): Character? {
        return null
    }
}