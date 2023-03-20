package at.xirado.githook.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkflowBody(
    var compare: String? = null,
    @SerialName("head_commit")
    var headCommit: GithubCommit? = GithubCommit(),
    var pusher: GithubUser? = GithubUser(),
    var before: String? = null,
    var created: Boolean? = null,
    var forced: Boolean? = null,
    @SerialName("base_ref")
    var baseRef: String? = null,
    var repository: GithubRepository? = GithubRepository(),
    var ref: String? = null,
    var deleted: Boolean? = null,
    var sender: GithubUser? = GithubUser(),
    var requestID: String? = null,
    var commits: ArrayList<GithubCommit> = arrayListOf(),
    var after: String? = null
)

@Serializable
data class GithubCommit(
    var committer: GithubUser? = GithubUser(),
    @SerialName("tree_id")
    var treeId: String? = null,
    var author: GithubUser? = GithubUser(),
    var distinct: Boolean? = null,
    var id: String? = null,
    var message: String? = null,
    var url: String? = null,
    var timestamp: String? = null
)

@Serializable
data class GithubUser(
    @SerialName("gists_url")
    var gistsUrl: String? = null,
    @SerialName("repos_url")
    var reposUrl: String? = null,
    @SerialName("following_url")
    var followingUrl: String? = null,
    @SerialName("starred_url")
    var starredUrl: String? = null,
    @SerialName("followers_url")
    var followersUrl: String? = null,
    var login: String? = null,
    var type: String? = null,
    var url: String? = null,
    @SerialName("subscriptions_url")
    var subscriptionsUrl: String? = null,
    @SerialName("received_events_url")
    var receivedEventsUrl: String? = null,
    @SerialName("avatar_url")
    var avatarUrl: String? = null,
    @SerialName("events_url")
    var eventsUrl: String? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var name: String? = null,
    @SerialName("site_admin")
    var siteAdmin: Boolean? = null,
    var id: Int? = null,
    @SerialName("gravatar_id")
    var gravatarId: String? = null,
    var email: String? = null,
    @SerialName("node_id")
    var nodeId: String? = null,
    @SerialName("organizations_url")
    var organizationsUrl: String? = null
)

@Serializable
data class GithubRepository(
    @SerialName("allow_forking")
    var allowForking: Boolean? = null,
    @SerialName("is_template")
    var isTemplate: Boolean? = null,
    @SerialName("stargazers_count")
    var stargazersCount: Int? = null,
    @SerialName("pushed_at")
    var pushedAt: Int? = null,
    var language: String? = null,
    @SerialName("subscription_url")
    var subscriptionUrl: String? = null,
    @SerialName("branches_url")
    var branchesUrl: String? = null,
    @SerialName("issue_comment_url")
    var issueCommentUrl: String? = null,
    @SerialName("labels_url")
    var labelsUrl: String? = null,
    @SerialName("subscribers_url")
    var subscribersUrl: String? = null,
    @SerialName("releases_url")
    var releasesUrl: String? = null,
    @SerialName("svn_url")
    var svnUrl: String? = null,
    var id: Int? = null,
    @SerialName("has_discussions")
    var hasDiscussions: Boolean? = null,
    @SerialName("master_branch")
    var masterBranch: String? = null,
    var forks: Int? = null,
    @SerialName("archive_url")
    var archiveUrl: String? = null,
    @SerialName("git_refs_url")
    var gitRefsUrl: String? = null,
    @SerialName("forks_url")
    var forksUrl: String? = null,
    var visibility: String? = null,
    @SerialName("statuses_url")
    var statusesUrl: String? = null,
    @SerialName("ssh_url")
    var sshUrl: String? = null,
    var license: String? = null,
    @SerialName("full_name")
    var fullName: String? = null,
    var size: Int? = null,
    @SerialName("languages_url")
    var languagesUrl: String? = null,
    @SerialName("clone_url")
    var cloneUrl: String? = null,
    @SerialName("collaborators_url")
    var collaboratorsUrl: String? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var name: String? = null,
    @SerialName("pulls_url")
    var pullsUrl: String? = null,
    @SerialName("default_branch")
    var defaultBranch: String? = null,
    @SerialName("hooks_url")
    var hooksUrl: String? = null,
    @SerialName("trees_url")
    var treesUrl: String? = null,
    @SerialName("tags_url")
    var tagsUrl: String? = null,
    @SerialName("contributors_url")
    var contributorsUrl: String? = null,
    var private: Boolean? = null,
    @SerialName("has_downloads")
    var hasDownloads: Boolean? = null,
    @SerialName("notifications_url")
    var notificationsUrl: String? = null,
    @SerialName("open_issues_count")
    var openIssuesCount: Int? = null,
    @SerialName("created_at")
    var createdAt: Int? = null,
    var description: String? = null,
    var watchers: Int? = null,
    @SerialName("deployments_url")
    var deploymentsUrl: String? = null,
    @SerialName("keys_url")
    var keysUrl: String? = null,
    @SerialName("has_projects")
    var hasProjects: Boolean? = null,
    var archived: Boolean? = null,
    @SerialName("has_wiki")
    var hasWiki: Boolean? = null,
    @SerialName("updated_at")
    var updatedAt: String? = null,
    @SerialName("comments_url")
    var commentsUrl: String? = null,
    @SerialName("stargazers_url")
    var stargazersUrl: String? = null,
    var disabled: Boolean? = null,
    @SerialName("git_url")
    var gitUrl: String? = null,
    @SerialName("has_pages")
    var hasPages: Boolean? = null,
    var owner: GithubUser? = GithubUser(),
    @SerialName("commits_url")
    var commitsUrl: String? = null,
    @SerialName("compare_url")
    var compareUrl: String? = null,
    @SerialName("git_commits_url")
    var gitCommitsUrl: String? = null,
    var topics: ArrayList<String> = arrayListOf(),
    @SerialName("blobs_url")
    var blobsUrl: String? = null,
    @SerialName("git_tags_url")
    var gitTagsUrl: String? = null,
    @SerialName("merges_url")
    var mergesUrl: String? = null,
    @SerialName("downloads_url")
    var downloadsUrl: String? = null,
    @SerialName("has_issues")
    var hasIssues: Boolean? = null,
    @SerialName("web_commit_signoff_required")
    var webCommitSignoffRequired: Boolean? = null,
    var url: String? = null,
    @SerialName("contents_url")
    var contentsUrl: String? = null,
    @SerialName("mirror_url")
    var mirrorUrl: String? = null,
    @SerialName("milestones_url")
    var milestonesUrl: String? = null,
    @SerialName("teams_url")
    var teamsUrl: String? = null,
    var fork: Boolean? = null,
    @SerialName("issues_url")
    var issuesUrl: String? = null,
    var stargazers: Int? = null,
    @SerialName("events_url")
    var eventsUrl: String? = null,
    @SerialName("issue_events_url")
    var issueEventsUrl: String? = null,
    @SerialName("assignees_url")
    var assigneesUrl: String? = null,
    @SerialName("open_issues")
    var openIssues: Int? = null,
    @SerialName("watchers_count")
    var watchersCount: Int? = null,
    @SerialName("forks_count")
    var forksCount: Int? = null,
    var homepage: String? = null,
    @SerialName("node_id")
    var nodeId: String? = null
)
