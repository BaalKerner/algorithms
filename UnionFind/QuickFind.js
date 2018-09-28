module.exports = class QuickFind {
  constructor(N) {
    this.id = [];

    for (let i = 0; i < N; i++) {
      this.id.push(i);
    }
  }

  connected(p1, p2) {
    return this.id[p1] === this.id[p2];
  }

  union(p1, p2) {
    const p1Id = this.id[p1];
    const p2Id = this.id[p2];
    this.id.forEach((value, i) => {
      if (value === p1Id) {
        this.id[i] = p2Id;
      }
    })
  }
};
