package com.zjjf.analysis.producer.login;

import java.io.IOException;

import com.zjjf.analysis.beans.vo.ResultEnum;

public interface ILoginSerivce {

	public ResultEnum doLoginOut() throws IOException;

	public ResultEnum getCheckcode() throws IOException;
}
