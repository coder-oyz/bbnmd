package com.yc.bbnmd1.dao.impl;

import com.yc.bbnmd1.domain.ReplayVO;
import com.yc.bbnmd1.domain.ViewDomain;
import com.yc.bbnmd1.entity.Board;
import com.yc.bbnmd1.entity.View;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface ViewMapper extends BaseMapper<View> {


    @Select(" select a.bid,bname,parentid,total ,tid ,title , date_format(modifytime,'%Y-%m-%d %H:%I:%S') as recentmodifytime ,uname from  " +
            " (select board.bid,bname,parentid,count(tid) as total FROM board left join topic on board.bid=topic.bid GROUP BY board.bid,bname,parentid)a left join  " +
            " (select tid,title,a.modifytime,uname,a.bid from  " +
            " (select bid,tid,title,modifytime,uname from topic left join user on topic.uid=user.uid  " +
            " )a,(select bid,max(modifytime) as modifytime from topic GROUP BY bid " +
            " )b where a.bid=b.bid and a.modifytime =b.modifytime)b on a.bid=b.bid ")
    public List<Board> findIndex();


    @SelectProvider(type = SqlMapper.class,method = "SelectByChangeIf")
    public List<ViewDomain> findByPageVO(ViewDomain t);



    @SelectProvider(type = SqlMapper.class,method = "find")
    public List<ReplayVO> findReplayVo(ReplayVO replayVO);
}
