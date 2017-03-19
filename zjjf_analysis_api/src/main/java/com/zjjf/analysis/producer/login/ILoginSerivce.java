package com.zjjf.analysis.producer.login;

import com.zjjf.analysis.beans.vo.ResultEnum;

import java.io.IOException;

public interface ILoginSerivce {

	public ResultEnum doLoginOut() throws IOException;

	public ResultEnum getCheckcode() throws IOException;
}
