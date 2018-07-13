/**
 *
 */
package com.vt.base.template.parser;

import com.mitchellbosecke.pebble.error.ParserException;
import com.mitchellbosecke.pebble.lexer.Token;
import com.mitchellbosecke.pebble.lexer.TokenStream;
import com.mitchellbosecke.pebble.node.RenderableNode;
import com.mitchellbosecke.pebble.node.expression.Expression;
import com.mitchellbosecke.pebble.tokenParser.AbstractTokenParser;
import com.vt.base.service.IDictItemService;
import com.vt.base.template.node.DictToCheckBoxNode;

/**
 * <p> Title: dict to checkbox token parser </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jul 3, 20151:46:00 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jul 3, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class DictToCheckBoxTokenParser extends AbstractTokenParser {
    /**
     * dict item service
     */
    private IDictItemService dictItemService;

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.tokenParser.TokenParser#getTag()
     */
    public String getTag() {
        return "dictToCheckBox";
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.tokenParser.TokenParser#parse(com.mitchellbosecke.pebble.lexer.Token)
     */
    public RenderableNode parse(Token token) throws ParserException {
        TokenStream stream = this.parser.getStream();
        int lineNumber = token.getLineNumber();

        stream.next();

        Expression<?> dictExpression = this.parser.getExpressionParser().parseExpression();

        stream.expect(Token.Type.EXECUTE_END);

        return new DictToCheckBoxNode(lineNumber, dictExpression, dictItemService);
    }

    /**
     * @return the dictItemService
     */
    public IDictItemService getDictItemService() {
        return dictItemService;
    }

    /**
     * @param dictItemService the dictItemService to set
     */
    public void setDictItemService(IDictItemService dictItemService) {
        this.dictItemService = dictItemService;
    }

}
