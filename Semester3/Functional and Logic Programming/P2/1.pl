% 1.
% a. Sort a list with removing the double values. E.g.: [4 2 6 2 3 4] --> [2 3 4 6]
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% write a predicate to sort every sublist with removing the doubles.
% Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] =>
% [1, 2, [1, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1], 7].

% a

% Model matematic:
% my_length(l1...ln, c) =
% 	c, n = 0
% 	my_length(l2...ln, c + 1), otherwise

% my_length(L:list, C:number, R:number)
% flow model: my_length(i, i, o)

my_length([], C, C).
my_length([_|T], C, R) :-
    NC is C + 1,
    my_length(T, NC, R).

% Model matematic:
% merge_sort(l1...ln) =
% 	[], n = o
% 	[l1], n = 1
% 	merge( merge_sort(l1....l(n/2)), merge_sort(l(n/2+1)...ln)), otherwise

% merge_sort(L:list, R:list)
% flow model: merge_sort(i, o)

merge_sort([], []).
merge_sort([E], [E]).
merge_sort(L, R) :-
    split(L, Left, Right),
    merge_sort(Left, RL),
    merge_sort(Right, RR),
    my_merge(RL, RR, R).

% Model matematic:
% split(l1...ln, c1...cm) =
% 	return, abs(m - n) <= 1 -> ori 1 ori 0 altfel merge in apel recursiv
% 	split(l2...ln, l1 + c1...cm), n > m

% split(L:list, LC:list, Left:list, Right:list)
% flow model: split(i, i, o, o)

split(L, LC, LC, L) :-
    my_length(L, 0, RL),
    my_length(LC, 0, RLC),
    Diff is RLC - RL,
    abs(Diff, AUX),
    AUX =< 1.
split([H|T], LC, Left, Right) :-
    my_append(LC, H, RA),
    split(T, RA, Left, Right).

% split(L:list, Left:list, Right:list)
% flow model: split(i, o, o)
split(L, Left, Right) :- split(L, [], Left, Right).

% Model matematic:
% my_append(l1...ln, e) =
% 	[e], n = 0
% 	l1 + my_append(l2...ln, e), otherwise

% my_append(L:list, E:number, R:list)
% flow model: my_append(i, i, o)

my_append([], E, [E]).
my_append([H|T], E, [H|R]) :-
    my_append(T, E, R).

% Model matematic:
% my_merge(l1...ln, r1...rm) =
% 	l1...ln, m = 0
% 	r1...rn, n = 0
% 	l1 + my_merge(l2...ln, r1...rm), l1 <= r1
% 	r1 + my_merge(l1...ln, r2...rm), l1 > r1

% my_merge(L:list, R:list, R:list)
% flow model: my_merge(i, i, o)
my_merge(L, [], L).
my_merge([], R, R).
my_merge([HL|TL], [HR|TR], [HL|R]) :- HL =< HR,
    my_merge(TL, [HR|TR], R).
my_merge([HL|TL], [HR|TR], [HR|R]) :- HL > HR,
    my_merge([HL|TL], TR, R).

% Model matematic:
% remove_doubles(l1...ln) =
% 	[], n = 0
% 	[l1], n = 1
% 	l1 + remove_doubles(l2...ln), l1 != l2
% 	remove_doubles(l2...ln), otherwise

% remove_doubles(L:list, R:list)
% flow model: remove_doubles(i, o)

remove_doubles([], []).
remove_doubles([E], [E]).
remove_doubles([H1, H2|T], [H1|R]) :- H1 =\= H2,
    remove_doubles([H2|T], R).
remove_doubles([H1, H2|T], R) :- H1 =:= H2,
    remove_doubles([H2|T], R).

% sort_list(L:list, R:list)
% flow model: sort_list(i, o)

sort_list(L, R) :-
    merge_sort(L, RS),
    remove_doubles(RS, R).

% b

% Model matematic:
% heterList(l1...ln) = 
% 	[], n = 0
% 	sort_list(l1) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], [HR|R]) :- is_list(H), !,
    sort_list(H, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).