package com.xy.wxxcx.common.resp;






import com.xy.wxxcx.common.constant.ErrCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 
 * @author Administrator
 * 
 * @param <T>
 */
@XmlRootElement
public class BaseResp<T> implements Serializable
{

	private static final long serialVersionUID = 1L;

	private int result;
	private String resultNote;
	private T detail;

	public BaseResp()
	{
		super();
	}

	public BaseResp(int result, String resultNote)
	{
		super();
		this.result = result;
		this.resultNote = resultNote;
	}
	
	public BaseResp(int result, String resultNote, T detail)
	{
		super();
		this.result = result;
		this.resultNote = resultNote;
		this.detail = detail;
	}

	public T getDetail()
	{
		return detail;
	}

	public void setDetail(T detail)
	{
		this.detail = detail;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String getResultNote()
	{
		return resultNote;
	}

	public void setResultNote(String resultNote)
	{
		this.resultNote = resultNote;
	}

	public static void setResp(boolean bool,BaseResp resp){
		if(bool){
			resp.setResult(ErrCode.SUCCESS);
			resp.setResultNote("成功");
		}else {

			resp.setResult(ErrCode.FAILED);
			resp.setResultNote("失败");
		}
	}
}
