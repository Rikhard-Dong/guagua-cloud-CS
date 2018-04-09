package com.guagua.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author ride
 * @date 18-4-2 上午10:36
 * <p>
 * 单例, 存放在线的客服id
 */
public class MemberSingleton {

    private static final Logger logger = LoggerFactory.getLogger(MemberSingleton.class);

    private static volatile MemberSingleton INSTANCE = null;

    // 保存在线客服所有ID
    private Set<Integer> onlineMembers;

    // 保存客服和对应的任务
    private Map<Integer, List<Integer>> members;

    // 客服处理接入的数量
    private Map<Integer, Integer> memberAccesses;

    /**
     * 私有构造函数
     */
    private MemberSingleton() {
        members = new HashMap<Integer, List<Integer>>();
        onlineMembers = new HashSet<Integer>();
        memberAccesses = new HashMap<Integer, Integer>();
    }

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static MemberSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (MemberSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MemberSingleton();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * 添加一个在线客服
     *
     * @param memberId memberId
     */
    public void addOnlineMember(Integer memberId) {
        onlineMembers.add(memberId);
    }

    /**
     * 删除一个在线客服
     *
     * @param memberId memberId
     */
    public void removeOnlineMember(Integer memberId) {
        onlineMembers.remove(memberId);
    }

    /**
     * 添加任务和对应客服
     *
     * @param taskId   任务id
     * @param memberId 客服id
     */
    public void addMemberTask(Integer taskId, Integer memberId) {
        List<Integer> memberIds = members.get(taskId);
        if (memberIds == null) {
            memberIds = new ArrayList<Integer>();
            members.put(taskId, memberIds);
        }
        memberIds.add(memberId);
    }

    /**
     * 根据任务移除某个客服, 即客服下线
     *
     * @param taskId
     * @param memberId
     */
    public void removeMemberTask(Integer taskId, Integer memberId) {
        for (Map.Entry<Integer, List<Integer>> entry : members.entrySet()) {
            if (entry.getKey().equals(taskId)) {
                List<Integer> list = entry.getValue();
                for (Integer var1 : list) {
                    if (var1.equals(memberId)) {
                        list.remove(var1);
                    }
                }
            }
        }
    }

    /**
     * 客服接入一个服务对象
     *
     * @param memberId
     */
    public void accessCustomer(Integer memberId) {
        Integer count = memberAccesses.get(memberId);
        if (count == null) {
            memberAccesses.put(memberId, 1);
            return;
        }
        count++;
        memberAccesses.put(memberId, count);
    }

    /**
     * 客服减少一个服务对象
     *
     * @param memberId
     */
    public void removeCustomer(Integer memberId) {
        Integer count = memberAccesses.get(memberId);
        if (count != null && count > 0) {
            count--;
            memberAccesses.put(memberId, count);
        } else {
            logger.error("更新客服处理服务对象的操作异常");
        }
    }

    /**
     * 移除某个任务, 即摸个任务结束了
     *
     * @param taskId
     */
    public void removeTask(Integer taskId) {
        for (Map.Entry<Integer, List<Integer>> entry : members.entrySet()) {
            if (entry.getKey().equals(taskId)) {
                members.remove(taskId);
            }
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static MemberSingleton getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(MemberSingleton INSTANCE) {
        MemberSingleton.INSTANCE = INSTANCE;
    }

    public Set<Integer> getOnlineMembers() {
        return onlineMembers;
    }

    public void setOnlineMembers(Set<Integer> onlineMembers) {
        this.onlineMembers = onlineMembers;
    }

    public Map<Integer, List<Integer>> getMembers() {
        return members;
    }

    public void setMembers(Map<Integer, List<Integer>> members) {
        this.members = members;
    }

    public Map<Integer, Integer> getMemberAccesses() {
        return memberAccesses;
    }

    public void setMemberAccesses(Map<Integer, Integer> memberAccesses) {
        this.memberAccesses = memberAccesses;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        List<Integer> var1 = map.get(1);
        if (var1 == null) {
            var1 = new ArrayList<Integer>();
            map.put(1, var1);
        }
        var1.add(233);

        System.out.println("first == > " + map);

        List<Integer> var2 = map.get(1);
        var2.add(2233);
        System.out.println("second == > " + map);
    }
}
