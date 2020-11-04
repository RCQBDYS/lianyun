import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listScript(query) {
  return request({
    url: '/system/script/list',
    method: 'get',
    params: query
  })
}

// 查询【scriptId脚本对应的信息】详细
export function getScript(scriptId) {
  return request({
    url: '/analysis/script/' + scriptId,
    method: 'get'
  })
}

//查询 脚本信息byProjectId
export function getScriptByPI(projectId) {
  return request({
    url: '/analysis/script/projectId/' + projectId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addScript(data) {
  return request({
    url: '/system/script',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateScript(data) {
  return request({
    url: '/system/script',
    method: 'put',
    data: data
  })
}

// 删除 脚本信息
export function delScript(scriptId) {
  return request({
    url: '/analysis/script/' + scriptId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportScript(query) {
  return request({
    url: '/system/script/export',
    method: 'get',
    params: query
  })
}
