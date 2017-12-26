% 15.
% a. Write a predicate to transform a list in a set, considering the first occurrence.
% Eg: [1,2,3,1,2] is transform in [1,2,3].
% b. Write a predicate to decompose a list in a list respecting the following: 
% [list of even numbers list of odd numbers] and also return the number of even numbers and the numbers of odd numbers.

% a

% Model matematic:
% remove_occurences(l1...ln, e) =
%    [], n = 0
%    l1 + remove_occurences(l2...ln, e), l1 != e
%    remove_occurences(l2...ln, e), l1 = e

% remove_occurences(L:list, E:number, R:list)
% remove_occurences(i, i, o)

remove_occurences([], _, []).
remove_occurences([H|T], E, R) :- H =:= E,
    remove_occurences(T, E, R).
remove_occurences([H|T], E, [H|R]) :- H =\= E,
    remove_occurences(T, E, R).

% Model matematic:
% list_to_set(l1...ln) =
% 	[], n = 0
% 	l1 + list_to_set(remove_occurences(l2...ln, l1)), otherwise
 	
% list_to_set(L:list, R:list)
% list_to_set(i, o)

list_to_set([], []).
list_to_set([H|T], [H|R]) :- 
    remove_occurences(T, H, R1),
    list_to_set(R1, R).

% b
% 
% Model matematic:
% decompose(l1...ln)
% 	[0, 0, [], []], n = 0
% 	{decompose(l2...ln), 1 + nr_even, l1 + even_list}, l1 % 2 = 0
% 	{decompose(l2...ln), 1 + nr_odd, l1 + odd_list}, l1 % 2 = 1

% decompose(L:list, R:list)
% decompose(i, o)

decompose([], [0, 0, [], []]).
decompose([H|T], [H1f, H2, [H|H3], H4]) :- H mod 2 =:= 0,
    decompose(T, [H1, H2, H3, H4]),
	H1f is H1 + 1.
decompose([H|T], [H1, H2f, H3, [H|H4]]) :- H mod 2 =:= 1,
    decompose(T, [H1, H2, H3, H4]),
	H2f is H2 + 1.