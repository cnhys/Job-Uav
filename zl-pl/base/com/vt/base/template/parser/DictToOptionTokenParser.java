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
import com.vt.base.template.node.DictToOptionNode;

/**
 * <h1>dict to option token parser</h1>
 *
 * @author Tony_Zhang
 * @version 1.0
 */
public class DictToOptionTokenParser extends AbstractTokenParser {
    /**
     * dict item service
     */
    private IDictItemService dictItemService;

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.tokenParser.TokenParser#getTag()
     */
    @Override
    public String getTag() {
        return "dictToOption";
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.tokenParser.TokenParser#parse(com.mitchellbosecke.pebble.lexer.Token)
     */
    @Override
    public RenderableNode parse(Token token) throws ParserException {
        TokenStream stream = this.parser.getStream();
        int lineNumber = token.getLineNumber();

        stream.next();

        Expression<?> dictExpression = this.parser.getExpressionParser().parseExpression();

        stream.expect(Token.Type.EXECUTE_END);

        return new DictToOptionNode(lineNumber, dictExpression, dictItemService);
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
