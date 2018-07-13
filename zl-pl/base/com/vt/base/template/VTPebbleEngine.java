/**
 *
 */
package com.vt.base.template;

import java.util.Set;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.extension.Extension;
import com.mitchellbosecke.pebble.loader.Loader;

/**
 * <h1>VT Pebble Engine</h1>
 * <p>扩展Pebble Engine增加自定义扩展支持</p>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public class VTPebbleEngine extends PebbleEngine {

    /**
     * 默认构造
     */
    public VTPebbleEngine() {
        super();
    }

    /**
     * 含参数构造
     *
     * @param loader
     */
    public VTPebbleEngine(Loader loader) {
        super(loader);
    }

    /**
     * 设置扩展
     *
     * @param extensions
     */
    public void setExtensions(Set<Extension> extensions) {
        if (extensions == null || extensions.size() == 0) {
            return;
        }

        for (Extension extension : extensions) {
            addExtension(extension);
        }
    }
}
