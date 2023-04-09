package com.starteam.core.lib.logs

import com.elvishew.xlog.flattener.PatternFlattener

/**
 *
 *     author : guanrunbai
 *     time   : 2021/06/03
 *     desc   :
 *     version: 1.0
 *
 */
class XFileFlatterer : PatternFlattener(DEFAULT_PATTERN) {

    companion object {
        private const val DEFAULT_PATTERN = "{d} {l}/{t}\n{m}\n"
    }
}