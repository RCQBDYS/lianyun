import request from '@/utils/request'

// 查询测试人员信息
export function getTester(query) {
return request({
  url: '/analysis/project/getTesterList',
  method: 'get',
  params: query
})
}
//查询开发人员信息
export function getDevelop(query) {
return request({
  url: '/analysis/project/getDevelopList',
  method: 'get',
  params: query
})
}

// 查询分析日志项目信息列表
export function listProject(query) {
  return request({
    url: '/analysis/project/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getProject(projectId) {
  return request({
    url: '/analysis/project/' + projectId,
    method: 'get'
  })
}

// 新增 增加日志分析项目信息
export function addProject(data) {
  return request({
    url: '/analysis/project/addProject',
    method: 'post',
    data: data
  })
}

// 修改 日志分析项目信息
export function updateProject(data) {
  return request({
    url: '/analysis/project',
    method: 'put',
    data: data
  })
}

// 删除 日志分析项目详细信息
export function delProject(projectId) {
  return request({
    url: '/analysis/project/' + projectId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportProject(query) {
  return request({
    url: '/analysis/project/export',
    method: 'get',
    params: query
  })
}
