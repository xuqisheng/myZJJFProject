package com.zjjf.analysis.services.view;

import java.util.List;

public interface IFilter {

	String[] getAuthorityColumn(String[] tableView, List<String> authorityFilter);
}
