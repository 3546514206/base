from git import Repo
import collections


def analyze_commits(repo_path):
    # 初始化Git仓库对象
    repo = Repo(repo_path)

    # 确保仓库已经克隆到本地
    assert not repo.bare

    # 统计提交次数的字典
    commit_counts = collections.defaultdict(int)

    # 遍历所有的提交记录
    for commit in repo.iter_commits():
        # 将作者的名字作为字典的键，每遇到一个提交，相应的值加1
        commit_counts[commit.author.name] += 1

    return commit_counts


def main():
    repo_path = input("Enter the path to the repository: ")
    commits = analyze_commits(repo_path)
    print("Commit counts by author:")
    for author, count in commits.items():
        print(f"{author}: {count}")


if __name__ == "__main__":
    main()
