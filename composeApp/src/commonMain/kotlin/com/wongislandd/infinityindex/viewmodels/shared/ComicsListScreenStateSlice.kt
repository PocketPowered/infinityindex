package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.models.local.Comic

class ComicsListScreenStateSlice: BaseListScreenStateSlice<Comic>(
    EntityType.COMICS
)