package com.wongislandd.infinityindex.entities.comics

import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class ComicsListScreenStateSlice: BaseListScreenStateSlice<Comic>(
    EntityType.COMICS
)