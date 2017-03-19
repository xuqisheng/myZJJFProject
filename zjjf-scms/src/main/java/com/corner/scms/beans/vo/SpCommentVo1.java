package com.corner.scms.beans.vo;

import java.io.Serializable;

public class SpCommentVo1 implements Serializable{
	
		//一星
		private SpCommentVo star1 = new SpCommentVo();
		//二星
		private SpCommentVo star2 = new SpCommentVo();
		//三星 
		private SpCommentVo star3 = new SpCommentVo();
		//四星
		private SpCommentVo star4 = new SpCommentVo();
		//五星
		private SpCommentVo star5 = new SpCommentVo();
		//综合
		private SpCommentVo starCount = new SpCommentVo();
	
		
		public SpCommentVo getStar1() {
			return star1;
		}
		public void setStar1(SpCommentVo star1) {
			this.star1 = star1;
		}
		public SpCommentVo getStar2() {
			return star2;
		}
		public void setStar2(SpCommentVo star2) {
			this.star2 = star2;
		}
		public SpCommentVo getStar3() {
			return star3;
		}
		public void setStar3(SpCommentVo star3) {
			this.star3 = star3;
		}
		public SpCommentVo getStar4() {
			return star4;
		}
		public void setStar4(SpCommentVo star4) {
			this.star4 = star4;
		}
		public SpCommentVo getStar5() {
			return star5;
		}
		public void setStar5(SpCommentVo star5) {
			this.star5 = star5;
		}
		public SpCommentVo getStarCount() {
			return starCount;
		}
		public void setStarCount(SpCommentVo starCount) {
			this.starCount = starCount;
		}
}
