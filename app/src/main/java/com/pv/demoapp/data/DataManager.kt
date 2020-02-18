package com.pv.demoapp.data

import com.pv.demoapp.data.local.db.DbHelper
import com.pv.demoapp.data.local.prefs.PrefHelper
import com.pv.demoapp.data.remote.ApiHelper

interface DataManager : PrefHelper, ApiHelper, DbHelper {
}