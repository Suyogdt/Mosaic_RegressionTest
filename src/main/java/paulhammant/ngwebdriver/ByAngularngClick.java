package paulhammant.ngwebdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;

public class ByAngularngClick extends ByAngular.BaseBy{
	
	    protected ByAngularngClick(String rootSelector, String datangclick) {
	    	super(rootSelector);
	        this.datangclick = datangclick;
		
	}

		private String datangclick;

	    protected Object getObject(SearchContext context, JavascriptExecutor javascriptExecutor) {
	        return javascriptExecutor.executeScript(
	                "var using = arguments[0] || document;\n" +
	                        "var rootSelector = '" + rootSelector + "';\n" +
	                        "var ngClick = '" + datangclick + "';\n" +
	                        "\n" +
	                        ByAngular.functions.get("findByngClick")
	                , context);
	    }

	    @Override
	    public String toString() {
	        return "ngClick(" + datangclick + ')';
	    }
	}
