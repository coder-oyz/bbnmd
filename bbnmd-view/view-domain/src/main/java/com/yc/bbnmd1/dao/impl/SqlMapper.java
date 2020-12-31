package com.yc.bbnmd1.dao.impl;

import com.yc.bbnmd1.domain.ReplayVO;
import com.yc.bbnmd1.domain.ViewDomain;

import java.util.ArrayList;
import java.util.List;

public class SqlMapper {
    public String SelectByChangeIf(ViewDomain t) throws Exception{


        StringBuffer sb=new StringBuffer();
        sb.append(" select b.*,a.total from ( ");
        sb.append("select replay.tid,count(*) as total from replay left join topic ");
        sb.append(" on topic.tid=replay.tid group by replay.tid ) a right join ( " );
        sb.append("select tid,title,content,DATE_FORMAT(publishtime,'%Y-%m-%d %H:%i') as publishtime, ");
        sb.append(" DATE_FORMAT(modifytime,'%Y-%m-%d %H:%i' ) as modifytime,t.uid,b.bid,bname, ");
        sb.append("parentid,uname,upwd,head,DATE_FORMAT(regtime,'%Y-%m-%d %H:%i')as regtime,gender from topic t left join board b on t.bid=b.bid  ");
        sb.append(" left join user u on u.uid=t.uid ) b on a.tid=b.tid where 1=1 ");
        if(null!=t) {
            if(null!=t.getBid()) {
                sb.append(" and b.bid= "+t.getBid());
            }
            if(null!=t.getTid()) {
                sb.append(" and b.tid="+t.getTid());
            }
            if(null!=t.getUid()) {
                sb.append(" and b.uid= "+t.getUid());
            }

        }
        sb.append(" order by b.modifytime desc ");

        return sb.toString();
    }

    public String find(ReplayVO t) throws Exception{
        StringBuffer sb=new StringBuffer();
        sb.append("select rid,title,content,DATE_FORMAT(publishtime,'%Y-%m-%d %H:%i') as publishtime, ");
        sb.append(" DATE_FORMAT(modifytime,'%Y-%m-%d %H:%i' ) as modifytime,r.uid,tid,uname,head ,");
        sb.append("DATE_FORMAT(regtime,'%Y-%m-%d %H:%i')as regtime,gender from replay r left join user u on r.uid=u.uid  ");
        sb.append(" where 1=1 ");
        List<Object> params=null;
        if(null!=t) {
            params=new ArrayList<Object>();
            if(null!=t.getRid()) {
                sb.append(" and rid= "+t.getRid());
            }
            if(null!=t.getTid()) {
                sb.append(" and tid= "+t.getTid());
            }
            if(null!=t.getUid()) {
                sb.append(" and u.uid= "+t.getUid());
            }
        }
        sb.append(" order by modifytime desc ");

        return sb.toString();
    }


}
