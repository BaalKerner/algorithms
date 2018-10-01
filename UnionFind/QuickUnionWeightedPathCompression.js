module.exports = class QuickUnionWeightedPathCompression {
  constructor(N) {
    this.id = [];
    this.size = [];

    for (let i = 0; i < N; i++) {
      this.id.push(i);
      this.size.push(1);
    }
  }

  root(p) {
    let i = p;

    while (i !== this.id[i]) {
      this.id[i] = this.id[ this.id[i] ];
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

    if (i === j) {
      return;
    }

    if (this.size[i] < this.size[j]) {
      this.id[i] = j;
      this.size[j] += this.size[i];
    } else {
      this.id[j] = i;
      this.size[i] += this.size[j];
    }
  }
};
