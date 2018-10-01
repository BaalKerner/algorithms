module.exports = class QuickUnion {
  constructor(N) {
    this.id = [];

    for (let i = 0; i < N; i++) {
      this.id.push(i);
    }
  }

  root(p) {
    let i = p;

    while (i !== this.id[i]) {
      i = this.id[i];
    }

    return i;
  }

  connected(p1, p2) {
    return this.root(p1) === this.root(p2);
  }

  union(p1, p2) {
    const i = this.root(p1);
    const j = this.root(p2);
    this.id[i] = j;
  }
};
