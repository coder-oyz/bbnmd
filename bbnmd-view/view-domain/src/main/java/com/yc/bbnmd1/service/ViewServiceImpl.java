package com.yc.bbnmd1.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bbnmd1.dao.impl.ReplayMapper;
import com.yc.bbnmd1.dao.impl.TopicMapper;
import com.yc.bbnmd1.dao.impl.ViewMapper;
import com.yc.bbnmd1.domain.PageDomain;
import com.yc.bbnmd1.domain.ReplayVO;
import com.yc.bbnmd1.domain.ViewDomain;
import com.yc.bbnmd1.entity.Board;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.entity.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ViewServiceImpl implements ViewService{
    @Autowired(required = false)
    ViewMapper viewMapper;


    @Autowired(required = false)
    TopicMapper topicMapper;

    @Autowired(required = false)
    ReplayMapper replayMapper;


    @Override
    public List<Board> getBoardAndTopic() {
        return viewMapper.findIndex();
    }
    //uid-uname
    @Override
    public Topic getTopicByBid(Integer bid) {
        return topicMapper.selectByPrimaryKey(bid);
    }



    //uid->uname
    @Override
    public PageDomain<ViewDomain> getTopicByPage(ViewDomain v) {
        System.out.println("viewDomainPage:    "+v);
        System.out.println("viewDomainPage:    "+v.getPage()+"..."+v.getPageSize());
        PageHelper.startPage(v.getPage(), v.getPageSize());



        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<ViewDomain> pageInfo = new PageInfo<ViewDomain>(viewMapper.findByPageVO(v));


        PageDomain<ViewDomain> pageDomain = new PageDomain<ViewDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(v.getPageSize());
        if(pageInfo.getTotal()%v.getPageSize()==0) {
            pageDomain.setMaxPage( Integer.parseInt(String.valueOf(pageInfo.getTotal()/v.getPageSize())));
        }else {
            pageDomain.setMaxPage( Integer.parseInt(String.valueOf(pageInfo.getTotal()/v.getPageSize()))+1);
        }
        pageDomain.setTotalPages(pageInfo.getPages());
        System.out.println("pageInfo.getPages()--------"+pageInfo.getPages());
        System.out.println("pageInfo.getPages()--------"+pageInfo.getPages());
        System.out.println("pageInfo.getPages()--------"+pageInfo.getPages());
        System.out.println("pageInfo.getPages()--------"+pageInfo.getPages());
        //List<Pic> list = picMapper.selectByExample(example);
        List<ViewDomain> r = new ArrayList<ViewDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (ViewDomain p : pageInfo.getList()) {
                r.add(p);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }




    //topic+repaly(uid-uname)
    @Override
    public List<ViewDomain> getTopic(ViewDomain vo) {
        return viewMapper.findByPageVO(vo);
    }
    @Override
    public List<ReplayVO> finds(ReplayVO vo) {
        return viewMapper.findReplayVo(vo);
    }
    @Override
    public int deleteReplay(Integer id) {
        return replayMapper.deleteByPrimaryKey(id);
    }
}
