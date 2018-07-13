package com.vt.fencing;

public interface IFencingConst {
	
	/**
	 * 是否审批
	 */
	Long IS_AUDIT = new Long(1);//已审批
	Long ISNOT_AUDIT = new Long(0);//未审批
	/**
	 * 是否删除
	 */
	Long IS_DELDETED = new Long(1);//已删除
	Long ISNOT_DELDETED = new Long(0);//未删除
	/**
	 * 是否默认
	 */
	Long IS_DEFAULT = new Long(1);//已默认
	Long ISNOT_DEFAULT = new Long(0);//未默认
	
	/**
	 * 是否审批
	 */
	Byte IS_BYTE_AUDIT = new Byte((byte) 1);//已审批
	Byte ISNOT_BYTE_AUDIT = new Byte((byte) 0);//未审批
	/**
	 * 是否删除
	 */
	Byte IS_BYTE_DELDETED = new Byte((byte) 1);//已删除
	Byte ISNOT_BYTE_DELDETED = new Byte((byte) 0);//未删除
	/**
	 * 是否默认
	 */
	Byte IS_BYTE_DEFAULT = new Byte((byte) 1);//已默认
	Byte ISNOT_BYTE_DEFAULT = new Byte((byte) 0);//未默认

}
