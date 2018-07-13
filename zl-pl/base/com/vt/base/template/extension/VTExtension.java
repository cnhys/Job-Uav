/**
 *
 */
package com.vt.base.template.extension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Extension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.extension.NodeVisitor;
import com.mitchellbosecke.pebble.extension.Test;
import com.mitchellbosecke.pebble.operator.BinaryOperator;
import com.mitchellbosecke.pebble.operator.UnaryOperator;
import com.mitchellbosecke.pebble.tokenParser.TokenParser;

/**
 * <h1>VT template extension</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public class VTExtension implements Extension {
    /**
     * filter map
     */
    private Map<String, Filter> filterMap;
    /**
     * parsers
     */
    private List<TokenParser> parsers;

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getBinaryOperators()
     */
    public List<BinaryOperator> getBinaryOperators() {
        return new ArrayList<BinaryOperator>();
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getFilters()
     */
    public Map<String, Filter> getFilters() {
        return filterMap;
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getFunctions()
     */
    public Map<String, Function> getFunctions() {
        return new HashMap<String, Function>();
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getGlobalVariables()
     */
    public Map<String, Object> getGlobalVariables() {
        return new HashMap<String, Object>();
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getNodeVisitors()
     */
    public List<NodeVisitor> getNodeVisitors() {
        return new ArrayList<NodeVisitor>();
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getTests()
     */
    public Map<String, Test> getTests() {
        return new HashMap<String, Test>();
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getTokenParsers()
     */
    public List<TokenParser> getTokenParsers() {
        return parsers;
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Extension#getUnaryOperators()
     */
    public List<UnaryOperator> getUnaryOperators() {
        return new ArrayList<UnaryOperator>();
    }

    /**
     * @param filterMap the filterMap to set
     */
    public void setFilterMap(Map<String, Filter> filterMap) {
        this.filterMap = filterMap;
    }

    /**
     * @return the parsers
     */
    public List<TokenParser> getParsers() {
        return parsers;
    }

    /**
     * @param parsers the parsers to set
     */
    public void setParsers(List<TokenParser> parsers) {
        this.parsers = parsers;
    }
}
